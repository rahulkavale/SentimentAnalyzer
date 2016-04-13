package models

import scala.collection.mutable.ListBuffer

object PartitionBasedSentimentAnalyzer extends SentimentAnalyzer[Int] {
  //top k elements out of given n elements
  //  time complexity O(n) + O(k)
  //  space complexity O(n)
  //  there is some constant space for helping state variables additional

  def getTopNElementsRec(accSize: Int, limit: Int,
                         currentBucketIdx: Int, acc: List[Int],
                         buckets: List[ListBuffer[Int]]): List[Int] = {
    if (accSize >= limit || currentBucketIdx < 0) {
      acc
    } else {
      val bucket = buckets(currentBucketIdx)
      val newElementsSize =
        if (accSize + bucket.size <= limit) {
          bucket.size
        } else {
          limit - accSize
        }

      getTopNElementsRec(accSize + newElementsSize,
        limit,
        currentBucketIdx - 1,
        acc ++ bucket.take(newElementsSize),
        buckets)
    }
  }

  def getTopNElements(sentiments: List[Int], k: Int) = {
    val TOTAL_BUCKETS = 6

    val buckets = Bucketizer.apply(sentiments, TOTAL_BUCKETS, sentimentScore)

    getTopNElementsRec(0, k, TOTAL_BUCKETS - 1, Nil, buckets)
  }

  override def sentimentScore: (Int) => Int = Math.abs

  override val sentimentOrdering: Ordering[Int] = Ordering.Int.reverse
}

object Bucketizer {

  def apply(xs: List[Int], size: Int,
            bucketingFunction: Int => Int) = {
    val buckets = List.fill(size)(ListBuffer[Int]())

    xs.foreach { element =>
      buckets(bucketingFunction(element)).append(element)
    }

    buckets
  }
}