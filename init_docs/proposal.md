
## 1. Executive Summary
The **Code Grapher** project aims to build a sophisticated context retrieval tool designed to solve the **"Multi Hunk Problem"** and mitigate hallucinations in Large Language Models (LLMs), specifically for Automated Program Repair (APR) tasks. By constructing a rich **Code Knowledge Graph (CKG)**, the system provides LLMs with structurally and semantically accurate context, enabling them to understand repository-level relationships that simple text retrieval (RAG) misses.

## 2. Problem & Solution

### The Challenge
*   **Fixed Context Windows**: LLMs cannot ingest entire repositories.
*   **The Multi Hunk Problem**: Bugs often span multiple files or functions. Retrieving only the immediate function is insufficient.
*   **Hallucinations**: Without a structural map, LLMs invent methods or misinterpret class hierarchies.

### The Solution: GraphRAG
We propose a multi-layered graph approach:
*   **Layer 1: Foundational Code Symbol/Entity Graph** (Repository Map)
    *   *Purpose*: Quick lookups, inheritance tracking, signature verification.
*   **Layer 2: Behavioral/Program Analysis Integration** (Execution Logic)
    *   *Purpose*: Control flow (CFG), Data flow (DDG), and Slicing.
    *   *Status*: **Planning / Early Prototyping**.
    *   Hopefully, can create and weave everything into one context for LLM APR

---

