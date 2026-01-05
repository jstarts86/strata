package com.jstarts.strata.core.entities;

public record VariableEntity(
        String id,
        String name,
        CodeEntityType type,
        SourceLocation location,
        String parentId,
        String declaredType,
        boolean isTyped,
        boolean isAssigned,
        boolean isParameterLike,
        String typeId,
        ScopeKind scope) implements CodeEntity {

    public VariableEntity {
        if (type == null)
            type = CodeEntityType.VARIABLE;
    }

    public enum ScopeKind {
        GLOBAL,
        LOCAL,
        CLASS_FIELD,
        INSTANCE_FIELD
    }
}
