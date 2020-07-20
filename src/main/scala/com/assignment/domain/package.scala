package com.assignment

import cats.data.NonEmptyList

import scala.collection.immutable.Queue

package object domain {
  type Node = Int
  type Nodes = Queue[Int]
  type Rows = NonEmptyList[Nodes]
}