package com.jstarts.strata.core.entities;

import java.util.LinkedHashMap;
import java.util.Map;

public sealed interface CodeEntity permits
        ClassEntity,
        FunctionEntity,
        VariableEntity,
        CallEntity,
        FileEntity,
        ImportEntity,
        PackageEntity,
        DecoratorEntity,
        FieldEntity,
        PythonTypeEntity {

    String id();

    String name();

    CodeEntityType type();

    SourceLocation location();

    String parentId();

    default Map<String, Object> toProperties() {
        Map<String, Object> props = new LinkedHashMap<>();
        props.put("id", id());
        props.put("name", name());
        props.put("type", type().toString());
        if (location() != null) {
            props.put("file", location().filePath());
            props.put("start_line", location().startLine());
            props.put("end_line", location().endLine());
            props.put("start_col", location().startCol());
            props.put("end_col", location().endCol());
            props.put("start_byte", location().startByte());
            props.put("end_byte", location().endByte());
        }
        return props;
    }
}
