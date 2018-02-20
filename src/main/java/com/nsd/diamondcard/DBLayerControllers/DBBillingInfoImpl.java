package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;
import com.nsd.diamondcard.Model.BillingInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBBillingInfoImpl implements DBBillingInfo {

    public DBBillingInfoImpl() {
        try {
            dao = DaoManager.createDao(DBConnectionFactory.getSource(), BillingInfo.class);
            if (!dao.isTableExists()) {
                TableUtils.createTable(dao.getConnectionSource(), BillingInfo.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Dao<BillingInfo, Long> dao;

    @Override
    public void createBillingInfo(BillingInfo billingInfo) {
        try {
            dao.create(billingInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBillingInfo(BillingInfo billingInfo) {
        try {
            dao.createOrUpdate(billingInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeBillingInfo(BillingInfo billingInfo) {
        try {
            dao.delete(billingInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BillingInfo> getBillingInfosWithForeign(long foreignId) {
        List<BillingInfo> resultList = null;
        try {
            resultList = dao.queryForEq("foreignUserID", foreignId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public BillingInfo getBillingInfo(long id) {
        BillingInfo result = null;
        try {
            result = dao.queryForId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<BillingInfo> getAllBillingInfos() {
        List<BillingInfo> resultList = null;
        try {
            resultList = dao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
