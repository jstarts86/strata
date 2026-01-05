# Code Grapher 2.0: Architecture Design Document

## 1. Executive Summary

Code Grapher 2.0 is a high-performance, scalable tool for indexing and analyzing source code repositories. Unlike traditional in-memory static analysis tools, Code Grapher 2.0 adopts a **Database-Centric ELT (Extract-Load-Transform)** architecture. By leveraging modern Java 21 features (Virtual Threads, Records) and a dedicated Graph Database (FalkorDB), it achieves constant memory footprint and enterprise-grade scalability, capable of handling monorepos with millions of lines of code.

## 2. Core Philosophy

The architecture is driven by three core principles:

1. **Scalability First**: No "load everything into RAM". Data is streamed to the database immediately.
2. **Modernity**: Utilization of cutting-edge language features to ensure longevity and performance.
3. **Data-Oriented Design**: Logic is expressed as data transformations (Cypher queries) rather than imperative object manipulation.

## 3. High-Level Architecture: The ELT Pipeline

The system operates in a strict three-stage pipeline:

### Stage 1: Extraction (Ingest)

- **Role**: The "Producer".
- **Mechanism**: A high-concurrency file walker using **Java Virtual Threads**.
- **Process**:
    1. Walks the file system.
    2. Parses each file in isolation using **Tree-sitter**.
    3. Extracts "Skeleton Nodes" (File, Class, Function definitions) and "Raw Edges" (Lexical containment).
- **Output**: A stream of lightweight DTOs (Data Transfer Objects).

### Stage 2: Loading (Persist)

- **Role**: The "Consumer".
- **Mechanism**: A batched writer connected to **FalkorDB**.
- **Process**:
    1. Receives DTOs from the extraction stream.
    2. Buffers them into efficient batches (e.g., 1000 nodes).
    3. Executes bulk `CREATE` Cypher commands.
- **Outcome**: The raw graph structure is persisted on disk.

### Stage 3: Transformation (Resolve)

- **Role**: The "Linker".
- **Mechanism**: In-database execution of **Cypher Command Objects**.
- **Process**:
    1. **Import Resolution**: Matches `IMPORT` nodes to `FILE` nodes.
    2. **Call Resolution**: Matches `CALL` nodes to `FUNCTION` nodes based on scope and imports.
    3. **Hierarchy Resolution**: Links classes to their parents.
- **Outcome**: A fully semantic, navigable code knowledge graph.

## 4. Technology Stack

| Component       | Technology                 | Justification                                                               |
| --------------- | -------------------------- | --------------------------------------------------------------------------- |
| **Language**    | **Java 25**                | Latest LTS release with advanced pattern matching and concurrency features. |
| **Concurrency** | **Virtual Threads (Loom)** | Enables "One-Thread-Per-File" model without overhead.                       |
| **Data Model**  | **Java Records**           | Immutable, concise data carriers perfect for DTOs.                          |
| **Parsing**     | **Tree-sitter**            | Robust, error-tolerant parsing for polyglot support.                        |
| **Database**    | **FalkorDB**               | High-performance Redis-based Graph DB.                                      |
| **Logic**       | **Pattern Matching**       | Replaces verbose Visitor patterns with concise `switch` expressions.        |


## 5. Design Patterns

The architecture explicitly avoids the "God Object" and "In-Memory DOM" antipatterns found in legacy tools.

- **Pipeline Pattern**: The macro-architecture is a linear pipeline, ensuring predictable data flow.
- **Command Pattern**: Transformation logic is encapsulated in `GraphCommand` objects (e.g., `ResolveImportsCommand`), allowing for composable and testable logic.
- **Producer-Consumer**: Decouples the CPU-bound parsing from the I/O-bound database writing.
- **Sealed Domain**: The 
    
    CodeEntity hierarchy is defined using `sealed interface`, strictly controlling the domain model.

## 6. Data Model (Graph Schema)

### Nodes

- `(:File {path, name})`
- `(:Class {name, qualifiedName})`
- `(:Function {name, signature})`
- `(:Variable {name, type})`

### Relationships

- `(:File)-[:CONTAINS]->(:Class)`
- `(:Class)-[:DECLARES]->(:Function)`
- `(:Function)-[:CALLS]->(:Function)` (Computed in Stage 3)
- `(:File)-[:IMPORTS]->(:File)` (Computed in Stage 3)

## 7. Future Roadmap: Dynamic Updates

To support real-time code graph updates, the architecture is designed to be **Incremental**:

- **File Watching**: A `WatchService` (or native equivalent) monitors the file system.
- **Incremental Pipeline**:
    1. **Event**: File `X.py` changes.
    2. **Purge**: Execute `MATCH (f:File {path: 'X.py'}) DETACH DELETE f`.
    3. **Re-Ingest**: Push `X.py` into the **Extraction Stage**.
    4. **Re-Link**: Run targeted Cypher queries to re-connect `X.py` to the graph.
- **Benefit**: The graph is always in sync with the code editor, enabling "Language Server Protocol" (LSP) capabilities.

## 8. Conclusion

Code Grapher 2.0 represents a paradigm shift in static analysis tooling. By treating code as data to be queried rather than objects to be managed, it solves the fundamental scalability challenges of the previous generation while offering a cleaner, more maintainable codebase.
