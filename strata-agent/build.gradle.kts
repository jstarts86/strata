dependencies {
    implementation(project(":strata-core"))
    implementation(project(":strata-indexer")) // Agent needs to trigger indexing

    // LangChain4j for the Agentic logic
    implementation("dev.langchain4j:langchain4j:0.31.0")
    implementation("dev.langchain4j:langchain4j-open-ai:0.31.0")
}
