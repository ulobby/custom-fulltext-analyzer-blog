package com.ulobby.fulltext;

import org.apache.lucene.analysis.Analyzer;

import org.neo4j.graphdb.index.fulltext.AnalyzerProvider;
import org.neo4j.helpers.Service;

@Service.Implementation(AnalyzerProvider.class)
public class CustomTransliteration extends AnalyzerProvider {

    public CustomTransliteration() {
        super("transliteration");
    }

    @Override
    public Analyzer createAnalyzer() {
        return new CustomTransliterationAnalyzer();
    }

    @Override
    public String description() {
        return "Analyzer with transliteration.";
    }
}
