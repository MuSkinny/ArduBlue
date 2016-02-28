package apps.marcofalanga.com.ardublue;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Marco on 27/02/16.
 */
public class Connect extends Main {

    public final BluetoothDevice mmDevice;
    public final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    public Connect(BluetoothDevice device) {
        BluetoothSocket tmp = null;
        mmDevice = device;
        try {
            tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
        }
        mmSocket = tmp;
    }

    public void run() {
        mBluetoothAdapter.cancelDiscovery();
        try {
            mmSocket.connect();
        } catch (IOException connectException) {
            try {
                mmSocket.close(); //its working with mSocket = ServerSocket
            } catch (IOException closeException) {
            }
            return;
        }
    }

    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
        }
    }
}

