package com.jstarts.strata.indexer.extraction.extractors;

import java.util.Deque;

import com.jstarts.strata.core.entities.CodeEntity;
import com.jstarts.strata.core.entities.FunctionEntity;
import com.jstarts.strata.indexer.extraction.FileInfo;

import io.github.treesitter.jtreesitter.Node;

public class FunctionEnitityExtractor {



    public FunctionEntity extract(Node rootNode, Deque<CodeEntity> extractionContext,String sourceCode, FileInfo fileInfo) {

        Node nameNode = rootNode.getChildByFieldName("name").orElse(null);

        return functionEntityBuilder(rootNode, nameNode, extractionContext, sourceCode, fileInfo);


    }
    private FunctionEntity functionEntityBuilder (Node rootNode, Node nameNode, Deque<CodeEntity> extractionContext,String sourceCode, FileInfo fileInfo)  {

        return null;

    }


    // private List<String> extractTypeParameters(Node functionNode, String
    // sourceCode) {
    // return
    // Optional.ofNullable(functionNode.getChildByFieldName("type_parameters"))
    // .map(Node::getChildren)
    // }

}
