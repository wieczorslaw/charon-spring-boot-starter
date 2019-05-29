package com.github.mkopylec.charon.test.assertions

import com.github.mkopylec.charon.test.stubs.OutgoingServer
import org.mockserver.verify.VerificationTimes
import org.springframework.http.HttpMethod

import static org.mockserver.verify.VerificationTimes.once

class OutgoingServerAssertion {

    private List<OutgoingServer> outgoingServers

    protected OutgoingServerAssertion(OutgoingServer... outgoingServers) {
        this.outgoingServers = outgoingServers
    }

    OutgoingServerAssertion haveReceivedNoRequest() {
        outgoingServers.each { it.verifyNoRequest() }
        return this
    }

    OutgoingServerAssertion haveReceivedRequest(HttpMethod requestMethod, String requestPath) {
        return haveReceivedRequest(requestMethod, requestPath, [:], '', once())
    }

    OutgoingServerAssertion haveReceivedRequest(HttpMethod requestMethod, String requestPath, Map<String, String> requestHeaders) {
        return haveReceivedRequest(requestMethod, requestPath, requestHeaders, '', once())
    }

    OutgoingServerAssertion haveReceivedRequest(HttpMethod requestMethod, String requestPath, Map<String, String> requestHeaders, String requestBody) {
        return haveReceivedRequest(requestMethod, requestPath, requestHeaders, requestBody, once())
    }

    OutgoingServerAssertion haveReceivedRequest(HttpMethod requestMethod, String requestPath, Map<String, String> requestHeaders, String requestBody, VerificationTimes count) {
        def error = '\r\n'
        def matchesCount = outgoingServers.count {
            try {
                it.verifyRequest(requestMethod, requestPath, requestHeaders, requestBody, count)
                return true
            } catch (AssertionError ex) {
                error += "\r\n[$it]\r\n$ex.message\r\n"
                return false
            }
        }
        if (matchesCount != 1) {
            throw new AssertionError(error)
        }
        return this
    }
}
