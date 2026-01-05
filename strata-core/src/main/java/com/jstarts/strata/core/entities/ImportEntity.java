package com.jstarts.strata.core.entities;

import java.util.List;
import java.util.Map;

public record ImportEntity(
        String id,
        String name,
        CodeEntityType type,
        SourceLocation location,
        String parentId,
        String fromModule,
        boolean isFromImport,
        boolean isRelative,
        int relativeLevel,
        List<String> importedNames,
        Map<String, String> aliases,
        Map<String, String> resolvedReferences) implements CodeEntity {
    public ImportEntity {
        if (type == null)
            type = CodeEntityType.IMPORT;
    }
}
