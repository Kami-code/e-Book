package com.bookstore.search;
import java.io.IOException;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

public class MyIKTokenizer extends Tokenizer {

    private IKSegmentation _IKImplement;
    private final CharTermAttribute termAtt = (CharTermAttribute)this.addAttribute(CharTermAttribute.class);
    private final OffsetAttribute offsetAtt = (OffsetAttribute)this.addAttribute(OffsetAttribute.class);
    private final TypeAttribute typeAtt = (TypeAttribute)this.addAttribute(TypeAttribute.class);
    private int endPosition;

    //useSmart：设置是否使用智能分词。默认为false，使用细粒度分词，这里如果更改为TRUE，那么搜索到的结果可能就少的很多
    public MyIKTokenizer(boolean useSmart) {
        this._IKImplement = new IKSegmentation(this.input, useSmart);
    }

    @Override
    public boolean incrementToken() throws IOException {
        this.clearAttributes();
        Lexeme nextLexeme = this._IKImplement.next();
        if (nextLexeme != null) {
            this.termAtt.append(nextLexeme.getLexemeText());
            this.termAtt.setLength(nextLexeme.getLength());
            this.offsetAtt.setOffset(nextLexeme.getBeginPosition(), nextLexeme.getEndPosition());
            this.endPosition = nextLexeme.getEndPosition();
            this.typeAtt.setType(String.valueOf(nextLexeme.getLexemeType()));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        this._IKImplement.reset(this.input);
    }

    @Override
    public final void end() {
        int finalOffset = this.correctOffset(this.endPosition);
        this.offsetAtt.setOffset(finalOffset, finalOffset);
    }

}
