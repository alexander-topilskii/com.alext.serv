package com.alext.serv.exceptions

class NoRoomException(override val message: String = "There is no room with given name") : Throwable()
class UnknownException(override val message: String = "There is no info but something went wrong") : Throwable()