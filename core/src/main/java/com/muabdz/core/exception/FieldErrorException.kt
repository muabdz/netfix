package com.muabdz.core.exception

class FieldErrorException(val errorFields: List<Pair<Int, Int>>): Exception()