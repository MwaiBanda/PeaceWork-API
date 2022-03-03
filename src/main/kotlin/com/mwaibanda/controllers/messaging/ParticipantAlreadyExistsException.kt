package com.mwaibanda.controllers.messaging

import java.lang.Exception

class ParticipantAlreadyExistsException : Exception(
    "Member already exists in chat :("
)