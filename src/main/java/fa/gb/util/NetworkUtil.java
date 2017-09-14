/**
 * Copyright (c) 2017 Graham Baitson - FA. All rights reserved.
 */
package fa.gb.util;

import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.validator.routines.InetAddressValidator;

/**
 * Networking utilities.
 *
 * @author Graham Baitson {@literal <grahambaitson@gmail.com>}
 */
@SuppressWarnings("PMD.EmptyCatchBlock")
public enum NetworkUtil {
    ; // no instances

    private static final long sleepTime = TimeUnit.SECONDS.toMillis(1);

    /**
     * Blocks until the given host and port are available or the given time has
     * elapsed.
     *
     * @param host
     *            the host
     * @param port
     *            the port
     * @param timeout
     *            the maximum time (in milliseconds) to wait before throwing an
     *            exception
     * @throws TimeoutException
     *             if the timeout is exceeded while waiting
     * @throws NullPointerException
     *             if the host is null
     * @throws IllegalArgumentException
     *             if the port is invalid or the timeout is not a positive number
     */
    public static void waitUntilAvailable(String host, int port, long timeout) throws TimeoutException {
        StopWatch watch = new StopWatch();
        watch.start();

        boolean isConnected = false;
        while (!isConnected) {
            isConnected = connect(host, port);

            try {
                Thread.sleep(sleepTime);
            } catch (Exception e1) {
                // Do nothing
            }

            if (watch.getTime() > timeout)
                throw new TimeoutException("Timed out waiting for a connection");
        }
    }

    /**
     * Blocks until the host of the given URL is available.
     *
     * @param url
     *            the URL of the host to wait for
     * @throws NullPointerException
     *             if the URL is null
     * @throws IllegalArgumentException
     *             if the URL is an empty string
     * @throws URISyntaxException
     *             if the URL is invalid
     */
    public static void waitUntilAvailable(String url) throws URISyntaxException {
        Validate.notEmpty(url, "The URL cannot be null or empty");
        URI uri = new URI(url);
        waitUntilAvailable(uri);
    }

    /**
     * Blocks until the host of the given URL is available.
     *
     * @param uri
     *            the URI of the host to wait for
     * @throws NullPointerException
     *             if the URL is null
     */
    public static void waitUntilAvailable(URI uri) {
        Validate.notNull(uri, "The URL cannot be null or empty");
        String host = uri.getHost();
        int port = uri.getPort();
        waitUntilAvailable(host, port);
    }

    /**
     * Blocks until the host of the given URL is available.
     *
     * @param uri
     *            the URI of the host to wait for
     * @param timeout
     *            the maximum time (in milliseconds) to wait before throwing an
     *            exception
     * @throws TimeoutException if the {@code timeout} is exceeded
     * @throws NullPointerException
     *             if the URL is null
     */
    public static void waitUntilAvailable(URI uri, long timeout) throws TimeoutException {
        Validate.notNull(uri, "The URL cannot be null or empty");
        String host = uri.getHost();
        int port = uri.getPort();
        waitUntilAvailable(host, port, timeout);
    }

    /**
     * Blocks until the given host and port are available.
     *
     * @param host
     *            the host
     * @param port
     *            the port
     * @throws NullPointerException
     *             if the host is null
     * @throws IllegalArgumentException
     *             if the port is invalid
     */
    public static void waitUntilAvailable(String host, int port) {
        boolean isConnected = false;
        while (!isConnected) {
            isConnected = connect(host, port);

            try {
                Thread.sleep(sleepTime);
            } catch (Exception e1) {
                // Do nothing
            }
        }
    }

    /**
     * Attempts to establish a connection to the given host and port.
     *
     * @param host
     *            the host to connect to
     * @param port
     *            the port to connect on
     * @return true if the connection was successful, false otherwise
     */
    private static boolean connect(String host, int port) {
        validateHost(host);
        validatePort(port);

        Socket socket = null;

        boolean isConnected;
        try {
            socket = new Socket(host, port);
            isConnected = true;
        } catch (Exception e) {
            isConnected = false;
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (Exception e) {
                // Do nothing
            }
        }

        return isConnected;
    }

    /**
     * Validates the given host.
     *
     * @param host
     *            the host to validate
     * @throws NullPointerException
     *             if the host is null
     * @throws IllegalArgumentException
     *             if the host is empty
     */
    public static void validateHost(String host) {
        Validate.notEmpty(host, "Host name cannot be null or empty");
    }

    /**
     * Validates the given port.
     *
     * @param port
     *            the port to validate
     * @throws IllegalArgumentException
     *             if the port is not in the range [1, 65535]
     */
    public static void validatePort(int port) {
        Validate.inclusiveBetween(1, 65535, port, String.format("Port must be in the range [1, 65535], but got %d", port));
    }

    public static void validateIpAddress(String ipAddress) {
        Validate.isTrue(InetAddressValidator.getInstance().isValid(ipAddress), String.format("Invalid IP address: %s", ipAddress));
    }

    public static void validateMacAddress(String macAddress) {
        Validate.isTrue(Pattern.matches("^([a-fA-F0-9]{2}[:\\.-]?){5}[a-fA-F0-9]{2}$", macAddress), String.format("Invalid MAC address: %s", macAddress));
    }

}
