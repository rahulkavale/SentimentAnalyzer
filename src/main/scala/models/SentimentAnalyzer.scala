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
