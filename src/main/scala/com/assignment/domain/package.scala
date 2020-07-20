package com.assignment

import cats.data.NonEmptyList

package object domain {
  type Node = Int
  type Nodes = List[Int]
  type Rows = NonEmptyList[Nodes]
}