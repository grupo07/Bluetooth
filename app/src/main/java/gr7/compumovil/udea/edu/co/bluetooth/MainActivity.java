package gr7.compumovil.udea.edu.co.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
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

public class MainActivity extends AppCompatActivity {

    Button encender, visibilidad, dispositivos, apague;
    private BluetoothAdapter blueA;
    private Set<BluetoothDevice> DispositivosVin;
    ListView vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        encender =(Button) findViewById(R.id.Prender);
        visibilidad =(Button)findViewById(R.id.visible);
        dispositivos =(Button)findViewById(R.id.lista);
        apague =(Button)findViewById(R.id.apagar);

        blueA = BluetoothAdapter.getDefaultAdapter();
        vista = (ListView)findViewById(R.id.listView);
    }
    public void encender(View v){
        if (!blueA.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(),"Encendido",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Ya esta encendido", Toast.LENGTH_LONG).show();
        }
    }

    public void apagar(View v){
        blueA.disable();
        Toast.makeText(getApplicationContext(),"Apagado" ,Toast.LENGTH_LONG).show();
    }

    public  void visible(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

    public void lista(View v){
        DispositivosVin = blueA.getBondedDevices();
        ArrayList list = new ArrayList();

        for(BluetoothDevice bt : DispositivosVin)
            list.add(bt.getName());
        Toast.makeText(getApplicationContext(),"Dispositivos Vinculados",Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        vista.setAdapter(adapter);
    }

}

