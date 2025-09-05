package com.theniche.colivin.controller;


import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
}

/*
        RoomController

GET /rooms/{roomId} → Get room details (independent of house)

PUT /rooms/{roomId} → Update a room

DELETE /rooms/{roomId} → Soft-delete room

GET /rooms?capacity=3&available=true → Search/filter rooms globally

 */
