package com.jstarts.strata.core.entities;

import java.util.List;
import java.util.Optional;

public record FunctionEntity(
        String id,
        String name,
        CodeEntityType type,
        SourceLocation location,
        String parentId,
        boolean isAsync,
        String returnType,
        String returnTypeId,
        List<Parameter> parameters,
        List<String> typeParameters) implements CodeEntity {

    public FunctionEntity {
        if (type == null)
            type = CodeEntityType.FUNCTION;
    }

    public record Parameter(
            ParameterKind kind,
            String name,
            Optional<String> typeAnnotation,
            Optional<String> defaultValue,
            Optional<String> typeId) {
    }

    public enum ParameterKind {
        NORMAL,
        TYPED,
        DEFAULT,
        TYPED_DEFAULT,
        LIST_SPLAT,
        DICT_SPLAT,
        POSITIONAL_SEPARATOR,
        KEYWORD_SEPARATOR
    }
}
