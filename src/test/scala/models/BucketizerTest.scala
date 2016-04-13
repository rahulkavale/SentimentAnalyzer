package models

import org.specs2.mutable.Specification

import scala.collection.mutable.ListBuffer

class BucketizerTest extends Specification {
  "Create buckets based on bucketing function" should {

    "identity bucketing function " in {
      val identityFunction: Int => Int = (a: Int) => a

      val buckets: List[ListBuffer[Int]] = Bucketizer.apply(List(5, 1, 3, 5, 2), 6, identityFunction)

      buckets.size mustEqual 6
      buckets.head.size mustEqual 0
      buckets(5).size mustEqual 2
      buckets(5) mustEqual ListBuffer(5, 5)
      buckets(2) mustEqual ListBuffer(2)
    }

    "absolute value based bucketing function " should {
      val absValue: Int => Int = Math.abs

      val buckets: List[ListBuffer[Int]] = Bucketizer.apply(List(5, -1, 1, 3, -5, 2, 0), 6, absValue)

      "should create empty buckets if no values belongs to it " in {
        buckets(4).size mustEqual 0
        buckets(4) mustEqual ListBuffer()
      }

      "should include all elements in given bucket" in {
        buckets.size mustEqual 6
        buckets(5).size mustEqual 2
        buckets(5) mustEqual ListBuffer(5, -5)

        buckets(1).size mustEqual 2
        buckets(1) mustEqual ListBuffer(-1, 1)

        buckets(0).size mustEqual 1
        buckets(0) mustEqual ListBuffer(0)
      }

      "should maintain order of elements as they occured" in {
        buckets(5) mustEqual ListBuffer(5, -5)

        buckets(1) mustEqual ListBuffer(-1, 1)

        buckets(2) mustEqual ListBuffer(2)

      }
    }
  }

}
