/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.implement;

import penjualan.entity.supplier;
import penjualan.koneksi.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import penjualan.entity.supplier;
import penjualan.interfc.supplierInterfc;

/**
 *
 * @author Lani
 */
public class supplierImplement implements supplierInterfc {

    public List<supplier> getAll() throws SQLException {
        Statement st = koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from supplier");
        List<supplier> list = new ArrayList<supplier>();
        while (rs.next()) {
            supplier sp = new supplier();
            sp.setkode_supplier(rs.getString("kode_supplier"));
            sp.setnama(rs.getString("nama_supplier"));
            sp.setalamat(rs.getString("alamat"));
            sp.setnope(rs.getString("notlp"));
            list.add(sp);
        }
        return list;
    }

//---untuk insert ke database---//
    public supplier insert(supplier o) throws SQLException {
        PreparedStatement st = koneksi.getConnection().prepareStatement("insert into supplier values (?,?,?,?)");
        st.setString(1, o.getkode_supplier());
        st.setString(2, o.getnama_supplier());
        st.setString(3, o.getalamat());
        st.setString(4, o.getnope());
        st.executeUpdate();
        return o;
    }
//--untuk update ke database--//

    public void update(supplier o) throws SQLException {
        PreparedStatement st = koneksi.getConnection().prepareStatement("update supplier set"
                + " nama_supplier=?,alamat=?,notlp=? where kode_supplier=?");
        st.setString(1, o.getnama_supplier());
        st.setString(2, o.getalamat());
        st.setString(3, o.getnope());
        st.setString(4, o.getkode_supplier());
        st.executeUpdate();
    }
//--untuk delete data berdasarkan kode_barang--//

    public void delete(String kode_supplier) throws SQLException {
        PreparedStatement st = koneksi.getConnection().prepareStatement("delete from supplier where kode_supplier=?");
        st.setString(1, kode_supplier);
        st.executeUpdate();
    }
}
