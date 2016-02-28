package apps.marcofalanga.com.ardublue;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class Main extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter;
    static final int REQUEST_ENABLE_BT = 1;
    Set<BluetoothDevice> PairedDevices;
    ListView ListBT_paired;
    ArrayList mPairedList = new ArrayList();
    ArrayAdapter mBTAdapter;
    Button onBT;
    public BluetoothSocket mmSocket;
    public BluetoothDevice MDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Iniz();
    }

    public void Iniz() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        ListBT_paired = (ListView) findViewById(R.id.Paired_list);
        mBTAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mPairedList);
        onBT = (Button) findViewById(R.id.Enable_Bt);

    }

    public void GetPaired(View view) {
        PairedDevices = mBluetoothAdapter.getBondedDevices();
        mBTAdapter.clear();
        if (PairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : PairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                mBTAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
        ListBT_paired.setAdapter(mBTAdapter);
    }

    public void OnBT(View view) {
        if (mBluetoothAdapter == null) {
            Show("Bluetooth not supported");
        } else if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        Show("Bluetooth is available");
    }

    public void Show(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void ControlRemote(View view) {
        Intent remote = new Intent(this, Control.class);
        startActivity(remote);
    }
}
