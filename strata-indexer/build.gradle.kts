dependencies {
    // Connect to the core module to get the Records/Interfaces
    implementation(project(":strata-core"))

    // High-performance Graph DB
    implementation("com.falkordb:jfalkordb:0.5.0")
    // Tree-Sitter parsing
    implementation("io.github.tree-sitter:jtreesitter:0.26.0")
}
