package com.jstarts.strata.indexer.extraction;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;



public class DirectoryWalker extends SimpleFileVisitor<Path> {

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		System.out.println("Finished walking directory");
        return FileVisitResult.CONTINUE;

	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        String name = dir.getFileName().toString();
        if (name.equals(".git") || name.equals("node_modules") || name.equals("__pycache__")) {
            return FileVisitResult.SKIP_SUBTREE; 
        }
        return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println("File: " + file);
        if(file.toString().endsWith(".py")) {
            System.out.println("Found python file");
            // scope.fork();  fork a  file processing
        }
        return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.err.println("Error accessing file: " + file + "due to: " + exc);
        return FileVisitResult.CONTINUE;
	}

}
