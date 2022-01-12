package com.mwaibanda.main.conversationController

import java.lang.Exception

class ParticipantAlreadyExistsException : Exception(
    "Member already exists in chat :("
)