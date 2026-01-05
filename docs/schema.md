# Code Grapher Data Model & Schema Specification

This document defines the entities, properties, and graph schema derived from the original `com.jstarts.codegrapher.core.entities` package. Use this as the canonical reference for the new project's data model.

## 1. Core Data Model (Entities)

All entities share a common set of base properties.

### 1.1 Base Entity ([CodeEntity](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/CodeEntity.java#10-86))
| Property | Type | Description |
| :--- | :--- | :--- |
| [id](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/CodeEntity.java#55-59) | `String` | Unique 16-char SHA256 hash of the location. |
| [name](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/CodeEntity.java#60-64) | `String` | Name of the entity (filename, class name, func name). |
| [type](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/CodeEntity.java#65-69) | `Enum` | `FILE`, `CLASS`, `FUNCTION`, `VARIABLE`, `CALL`, `IMPORT`, `TYPE`. |
| [parentId](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/CodeEntity.java#75-79) | `String` | ID of the parent entity (e.g., class containing a method). |
| [location](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/CodeEntity.java#70-74) | `Object` | Source code coordinates (see below). |

**SourceLocation**:
*   `filePath` (String)
*   `startLine`, `endLine` (int)
*   `startCol`, `endCol` (int)
*   `startByte`, `endByte` (int)

---

### 1.2 File Entity ([FileEntity](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/FileEntity.java#7-49))
Represents a source file (module).
| Property | Type | Description |
| :--- | :--- | :--- |
| [moduleName](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/FileEntity.java#30-34) | `String` | The logical module name (e.g., `utils` for `utils.py`). |

### 1.3 Class Entity ([ClassEntity](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/ClassEntity.java#10-43))
Represents a class definition.
| Property | Type | Description |
| :--- | :--- | :--- |
| [superClasses](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/ClassEntity.java#24-28) | `List<String>` | Names of parent classes (e.g., `["BaseModel", "Mixin"]`). |

### 1.4 Function Entity ([FunctionEntity](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/FunctionEntity.java#10-99))
Represents a function or method definition.
| Property | Type | Description |
| :--- | :--- | :--- |
| [isAsync](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/FunctionEntity.java#60-64) | `boolean` | `true` if `async def`. |
| [returnType](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/FunctionEntity.java#65-69) | `String` | The raw return type annotation string. |
| [returnTypeId](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/FunctionEntity.java#70-74) | `String` | ID of the resolved return type entity. |
| [parameters](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/FunctionEntity.java#75-79) | `List<Param>` | List of parameters. |

**Parameter Object**:
*   [name](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/CodeEntity.java#60-64) (String)
*   `kind` (Enum: `NORMAL`, `TYPED`, `DEFAULT`, `SPLAT`, etc.)
*   `typeAnnotation` (String)
*   [typeId](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/VariableEntity.java#52-56) (String)
*   `defaultValue` (String)

### 1.5 Variable Entity ([VariableEntity](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/VariableEntity.java#6-86))
Represents a variable assignment or field.
| Property | Type | Description |
| :--- | :--- | :--- |
| [scope](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/VariableEntity.java#42-46) | `Enum` | `GLOBAL`, `LOCAL`, `CLASS_FIELD`, `INSTANCE_FIELD`. |
| [declaredType](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/VariableEntity.java#47-51) | `String` | Raw type annotation. |
| [typeId](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/VariableEntity.java#52-56) | `String` | ID of the resolved type entity. |
| [isAssigned](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/VariableEntity.java#62-66) | `boolean` | `true` if it has an initial value. |

### 1.6 Call Entity ([CallEntity](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/CallEntity.java#5-66))
Represents a function call site.
| Property | Type | Description |
| :--- | :--- | :--- |
| [callee](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/CallEntity.java#16-20) | `String` | The raw string being called (e.g., `os.path.join`). |
| [argCount](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/CallEntity.java#21-25) | [int](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/Main.java#157-167) | Number of arguments passed. |
| [resolvedFunctionId](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/CallEntity.java#26-30)| `String` | **Critical**: The ID of the [FunctionEntity](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/FunctionEntity.java#10-99) this call targets. |

### 1.7 Import Entity ([ImportEntity](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/ImportEntity.java#18-117))
Represents an import statement.
| Property | Type | Description |
| :--- | :--- | :--- |
| [fromModule](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/ImportEntity.java#57-61) | `String` | The module being imported from (e.g., `numpy`). |
| [importedNames](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/ImportEntity.java#77-81) | `List<String>` | List of names imported. |
| [aliases](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/ImportEntity.java#82-86) | `Map<String,String>`| Alias mapping (e.g., `{"np": "numpy"}`). |
| [resolvedReferences](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/ImportEntity.java#87-91)| `Map<String,String>` | Map of [importedName](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/ImportEntity.java#77-81) -> `EntityID`. |

### 1.8 Type Entity ([PythonTypeEntity](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/PythonTypeEntity.java#12-83))
Represents a canonical type definition.
| Property | Type | Description |
| :--- | :--- | :--- |
| [signature](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/PythonTypeEntity.java#39-43) | `String` | Unique type signature (e.g., `List[int]`). |
| [generics](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/PythonTypeEntity.java#44-48) | `List<Type>` | Nested generic types. |

---

## 2. Graph Schema (FalkorDB)

This schema maps the entities above to Nodes and Relationships in the graph database.

### 2.1 Nodes (Labels)
*   `:File`
*   `:Class`
*   `:Function`
*   `:Variable`
*   `:Type`

*(Note: [Call](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/CallEntity.java#54-57) and [Import](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/ImportEntity.java#62-66) are typically modeled as **Relationships** in the final graph, though the extractor produces them as entities first.)*

### 2.2 Relationships (Edges)

| Source Node | Relationship | Target Node | Description |
| :--- | :--- | :--- | :--- |
| `:File` | `[:CONTAINS]` | `:Class` / `:Function` | Structural containment. |
| `:Class` | `[:DECLARES]` | `:Function` / `:Variable` | Methods and fields. |
| `:Function` | `[:DECLARES]` | `:Variable` | Local variables. |
| `:Class` | `[:INHERITS_FROM]`| `:Class` | Inheritance hierarchy. |
| `:Function` | `[:CALLS]` | `:Function` | **Call Graph**: Derived from `CallEntity.resolvedFunctionId`. |
| `:File` | `[:IMPORTS]` | `:File` | **Dependency Graph**: Derived from [ImportEntity](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/ImportEntity.java#18-117). |
| `:Variable` | `[:HAS_TYPE]` | `:Type` | Type system linkage. |
| `:Function` | `[:RETURNS]` | `:Type` | Return type linkage. |

## 3. Implementation Notes for Rewrite

1.  **DTOs**: Create Java Records for each Entity listed in Section 1.
2.  **Resolution**:
    *   Use [CallEntity](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/CallEntity.java#5-66) to generate `[:CALLS]` edges.
    *   Use [ImportEntity](file:///Users/john/Coding/IdeaProjects/code-grapher/src/main/java/com/jstarts/codegrapher/core/entities/ImportEntity.java#18-117) to generate `[:IMPORTS]` edges.
3.  **IDs**: Maintain the SHA256 ID generation strategy based on `SourceLocation` to ensure deterministic IDs across runs.

