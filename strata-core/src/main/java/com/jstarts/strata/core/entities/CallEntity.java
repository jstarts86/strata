package com.jstarts.strata.core.entities;

public record CallEntity(
        String id,
        String name,
        CodeEntityType type,
        SourceLocation location,
        String parentId,
        String callee,
        int argCount,
        String resolvedFunctionId) implements CodeEntity {
    public CallEntity {
        if (type == null)
            type = CodeEntityType.CALL;
    }
}
