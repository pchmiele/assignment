package com.assignment.domain

sealed trait AssignmentError extends Throwable

sealed trait ParsingError extends AssignmentError

case object NoInputData extends ParsingError
case class WrongRowLength(row: Int, rowLength: Int, expectedRowLength: Int) extends ParsingError
case class RowContainsInvalidCharacter(row: Int) extends ParsingError