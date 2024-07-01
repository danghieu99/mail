package com.example.kafkalistener.services.kafkalistener;

import java.util.List;

public interface KafkaListener {

    public void run() throws InterruptedException;

    public void listen();

    public String subscribe(List<String> topics);
}
