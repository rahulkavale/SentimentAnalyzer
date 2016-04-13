package models

import org.specs2.mutable.Specification

class PartitionBasedSentimentAnalyzerTest extends Specification {
  "Partition based sentiment analyzer" should {
    "top N sentiments" in {
      PartitionBasedSentimentAnalyzer.get(List(-5, -4, 5, 3, 1, 4), 4) mustEqual List(-5, 5, -4, 4)

      PartitionBasedSentimentAnalyzer.get(List(-5, -4, 5, 3, 1, 4), 1) mustEqual List(-5)

      PartitionBasedSentimentAnalyzer.get(List(-5, -4, 5, 3, 1, 4), 10) mustEqual List(-5, 5, -4, 4, 3, 1)
    }
  }
}
