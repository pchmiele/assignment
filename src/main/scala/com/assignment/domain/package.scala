package com.assignment

import cats.data.NonEmptyList

package object domain {
  type Nodes = List[Int]
  type Rows = NonEmptyList[Nodes]
}