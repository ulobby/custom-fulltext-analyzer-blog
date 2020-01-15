package com.ulobby.fulltext;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.standard.std40.StandardTokenizer40;
import org.apache.lucene.util.IOUtils;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.Reader;

/**
 * {@link Analyzer} with transliteration.
 * <p>
 * Supports an external list of stopwords (words that will not be indexed at
 * all). A default set of stopwords is used unless an alternative list is
 * specified.
 * </p>
 */
public final class CustomTransliterationAnalyzer extends Analyzer {
    /**
     * Creates
     * {@link org.apache.lucene.analysis.Analyzer.TokenStreamComponents}
     * used to tokenize all the text in the provided {@link Reader}.
     *
     * @return {@link org.apache.lucene.analysis.Analyzer.TokenStreamComponents}
     *         built from a {@link StandardTokenizer} filtered with
     *         {@link StandardFilter}, {@link LowerCaseFilter}, {@link StopFilter}
     *         , and {@link CzechStemFilter} (only if version is &gt;= LUCENE_31). If
     *         a stem exclusion set is provided via
     *         {@link #CzechAnalyzer(CharArraySet, CharArraySet)} a
     *         {@link SetKeywordMarkerFilter} is added before
     *         {@link CzechStemFilter}.
     */
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        final Tokenizer source;
        if (getVersion().onOrAfter(Version.LUCENE_4_7_0)) {
            source = new StandardTokenizer();
        } else {
            source = new StandardTokenizer40();
        }
        TokenStream result = new StandardFilter(source);
        result = new LowerCaseFilter(result);
        result = new ASCIIFoldingFilter(result);
        return new TokenStreamComponents(source, result);
    }
}
