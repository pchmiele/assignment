package com.assignment.domain

sealed trait AssignmentError extends Throwable

sealed trait ParsingError extends AssignmentError
case object NoInputData extends ParsingError