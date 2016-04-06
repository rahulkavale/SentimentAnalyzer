package models

class SentimentAnalyzerSpec extends org.specs2.mutable.Specification {

  "Sentiment analyzer" should {
    "Absolute value sentiment analyzer" in {
      val sentimentAnalyzer = new SentimentAnalyzer[Int] {
        override def sentimentScore: (Int) => Int = Math.abs

        override val sentimentOrdering: Ordering[Int] = Ordering.Int.reverse
      }

      sentimentAnalyzer.sort(List(-4, 3)) mustEqual List(-4, 3)
      sentimentAnalyzer.sort(List(3, -4)) mustEqual List(-4, 3)

      sentimentAnalyzer.sort(List(-1, 2)) mustEqual List(2, -1)
      sentimentAnalyzer.sort(List(2, -1)) mustEqual List(2, -1)

      sentimentAnalyzer.sort(List(-4, 5)) mustEqual List(5, -4)
      sentimentAnalyzer.sort(List(5, -4)) mustEqual List(5, -4)

      sentimentAnalyzer.sort(List(-5, -4)) mustEqual List(-5, -4)
      sentimentAnalyzer.sort(List(-4, -5)) mustEqual List(-5, -4)
    }
  }
}
