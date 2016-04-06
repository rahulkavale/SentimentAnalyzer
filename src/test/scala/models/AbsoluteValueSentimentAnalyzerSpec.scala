package models

import org.specs2.mutable.Specification

class AbsoluteValueSentimentAnalyzerSpec extends Specification {

  "Absolute value based sentiment analyzer" should {
    "top N sentiments based on absolute sentiment value" in {
      AbsoluteValueSentimentAnalyzer.get(List(-5, 6, -4, 5, 3, 1, 4), 4) mustEqual List(6, -5, 5, -4)
    }
  }

}
