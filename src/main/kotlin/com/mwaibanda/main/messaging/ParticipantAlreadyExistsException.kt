package com.mwaibanda.main.messaging

import java.lang.Exception

class ParticipantAlreadyExistsException : Exception(
    "Member already exists in chat :("
)