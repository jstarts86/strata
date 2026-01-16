package com.jstarts.strata.indexer.config;

import java.lang.foreign.Arena;
import java.lang.foreign.SymbolLookup;
import java.nio.file.Path;

import io.github.treesitter.jtreesitter.Language;

public class TreeSitterConfig {
    public static Language loadPython() {
        Path libraryPath = Path.of("native.libtree-sitter-python.dylib");
        try(Arena arena = Arena.ofConfined()) {
            SymbolLookup lookup = SymbolLookup.libraryLookup(libraryPath, arena);
            return Language.load(lookup, "tree_sitter_python");
        }
    }

    
}
