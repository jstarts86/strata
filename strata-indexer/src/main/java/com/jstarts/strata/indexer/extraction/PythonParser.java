package com.jstarts.strata.indexer.extraction;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.BlockingQueue;

import com.jstarts.strata.core.entities.ClassEntity;
import com.jstarts.strata.core.entities.CodeEntity;
import com.jstarts.strata.core.entities.FunctionEntity;
import com.jstarts.strata.indexer.config.TreeSitterConfig;

import io.github.treesitter.jtreesitter.Language;
import io.github.treesitter.jtreesitter.Node;
import io.github.treesitter.jtreesitter.Parser;

public class PythonParser {
    private final Language python = TreeSitterConfig.loadPython();
    private final String filePath;
    private final String parentDirectory;
    public PythonParser(String filePath, String parentDirectory) {
        this.filePath = filePath;
        this.parentDirectory = parentDirectory;
    }

    private final Deque<CodeEntity> extractionContext = new ArrayDeque<>();

    public void parse(String sourceCode, BlockingQueue<CodeEntity> queue,  FileInfo fileInfo) {
        try (Parser parser = new Parser(python)) {
            parser.parse(sourceCode).ifPresent(tree -> {
                Node rootNode = tree.getRootNode();
                extractCodeEntitiesFromRoot(rootNode, queue, sourceCode);
            });
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void extractCodeEntitiesFromRoot(Node rootNode, BlockingQueue<CodeEntity> queue,  String sourceCode, FileInfo fileInfo) {
        String nodeType = rootNode.getType();

        switch (nodeType) {
            case "function_definition" -> {
                queue.add()
            }
        }
    }


// /65/󰬷 /CallEntity.java
// /66/󰬷 /ClassEntity.java
// /67/󰬷 /CodeEntity.java
// /68/󰬷 /CodeEntityType.java
// /69/󰬷 /DecoratorEntity.java
// /70/󰬷 /FieldEntity.java
// /71/󰬷 /FileEntity.java
// /72/󰬷 /FunctionEntity.java
// /73/󰬷 /ImportEntity.java
// /74/󰬷 /PackageEntity.java
// /75/󰬷 /PythonTypeEntity.java
// /76/󰬷 /SourceLocation.java
// /77/󰬷 /VariableEntity.java

    
}
