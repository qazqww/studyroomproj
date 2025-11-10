CREATE UNIQUE INDEX uq_member_email ON member (email);

CREATE INDEX idx_reservation_starttime ON reservation (start_time);
CREATE INDEX idx_reservation_roomno ON reservation (room_no);