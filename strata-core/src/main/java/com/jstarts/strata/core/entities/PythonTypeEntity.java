package com.jstarts.strata.core.entities;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record PythonTypeEntity(
        String id,
        String name,
        CodeEntityType type,
        SourceLocation location,
        String parentId,
        String signature,
        List<PythonTypeEntity> generics) implements CodeEntity {
    public PythonTypeEntity {
        if (type == null)
            type = CodeEntityType.TYPE;
    }

    @Override
    public Map<String, Object> toProperties() {
        Map<String, Object> props = CodeEntity.super.toProperties();
        props.put("signature", signature);
        if (generics != null && !generics.isEmpty()) {
            props.put("generics", generics.stream()
                    .map(PythonTypeEntity::signature)
                    .collect(Collectors.toList()));
        }
        return props;
    }
}
