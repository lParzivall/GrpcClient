package ru.mitrasoft.grpcClient.services;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mitrasoft.grpc.UserServiceGrpc;
import ru.mitrasoft.grpc.UserServiceOuterClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {

    public List<String> getAllAppUsers(String username, String password) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8081)
                .usePlaintext().build();

        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        UserServiceOuterClass.UserServiceRequest request = UserServiceOuterClass.UserServiceRequest
                .newBuilder().setUsername(username).setPassword(password).build();

        Iterator<UserServiceOuterClass.UserServiceResponse> response = stub.getUsers(request);
        List<String> appUsers = new ArrayList<>();
        while (response.hasNext()) {
            appUsers.add(response.next().toString());
        }

        channel.shutdownNow();
        return appUsers;
    }
}
