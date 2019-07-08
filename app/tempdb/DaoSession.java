package tempdb;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import tempdb.TreasureChestModel;

import tempdb.TreasureChestModelDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig treasureChestModelDaoConfig;

    private final TreasureChestModelDao treasureChestModelDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        treasureChestModelDaoConfig = daoConfigMap.get(TreasureChestModelDao.class).clone();
        treasureChestModelDaoConfig.initIdentityScope(type);

        treasureChestModelDao = new TreasureChestModelDao(treasureChestModelDaoConfig, this);

        registerDao(TreasureChestModel.class, treasureChestModelDao);
    }
    
    public void clear() {
        treasureChestModelDaoConfig.getIdentityScope().clear();
    }

    public TreasureChestModelDao getTreasureChestModelDao() {
        return treasureChestModelDao;
    }

}
