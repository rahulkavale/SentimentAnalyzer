package models

trait SentimentAnalyzer[T] {
  def sentimentScore: T => Int

  val sentimentOrdering: Ordering[Int]

  def get(sentiments: List[T], max: Int) =
    sort(sentiments).take(max)

  def sort(sentiments: List[T]): List[T] = {
    sentiments.sortBy(sentimentScore)(sentimentOrdering)
  }
}

case object AbsoluteValueSentimentAnalyzer extends SentimentAnalyzer[Int] {
  override def sentimentScore: (Int) => Int = Math.abs

  override val sentimentOrdering: Ordering[Int] = Ordering.Int.reverse
}

object SentimentAnalyzer {
  def absoluteValueAnalyzer = AbsoluteValueSentimentAnalyzer
}
