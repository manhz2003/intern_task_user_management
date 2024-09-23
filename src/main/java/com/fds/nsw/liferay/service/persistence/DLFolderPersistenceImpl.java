package com.fds.nsw.liferay.service.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fds.flex.common.utility.string.StringBundler;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.kernel.dao.orm.BasePersistence;
import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.model.DLFolder;
import com.fds.nsw.liferay.repository.DlfolderRepository;
import com.fds.nsw.liferay.service.exception.NoSuchFolderException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DLFolderPersistenceImpl extends BasePersistence {
	
	@Autowired
	@Qualifier("lrQueryFactory")
	QueryFactory<DLFolder> queryFactory;

	@Autowired
	DlfolderRepository repository;

	public DLFolder create(long id) {
		DLFolder dlFolder = new DLFolder();

		String uuid = UUID.randomUUID().toString();

		dlFolder.setUuid(uuid);

		return dlFolder;
	}
	public DLFolder fetchByG_P_N(long groupId, long parentFolderId, String name) throws SystemException {
		return fetchByG_P_N(groupId, parentFolderId, name, true);
	}
	public DLFolder fetchByG_P_N(long groupId, long parentFolderId, String name, boolean retrieveFromCache)
			throws SystemException {

		DLFolder dlFolder = null;

		if (dlFolder == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DLFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_P_N_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_N_PARENTFOLDERID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_G_P_N_NAME_1);
			} else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_P_N_NAME_3);
				} else {
					query.append(_FINDER_COLUMN_G_P_N_NAME_2);
				}
			}

			query.append(ORDER_BY_JPQL);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql)
						.entityClass(DLFolder.class).build();

				builder.appendNamedParameterMap("groupId", groupId);

				builder.appendNamedParameterMap("parentFolderId", parentFolderId);

				if (name != null) {
					builder.appendNamedParameterMap("name", name);
				}

				dlFolder = (DLFolder) queryFactory.getSingleResult(builder);

			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return dlFolder;
	}
	// todo update persistent function
	public DLFolder fetchByPrimaryKey(long id) throws SystemException {
		DLFolder dlFolder = null;

		if (dlFolder == null) {

			try {
				Optional<DLFolder> optional = repository.findById(id);
				if (optional.isPresent()) {
					dlFolder = optional.get();
				}

			} catch (Exception e) {

				throw processException(e);
			} finally {

			}
		}

		return dlFolder;
	}
	
	public DLFolder findByG_P_N(long groupId, long parentFolderId, String name)
			throws NoSuchFolderException, SystemException {
		DLFolder dlFolder = fetchByG_P_N(groupId, parentFolderId, name);

		if (dlFolder == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", parentFolderId=");
			msg.append(parentFolderId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchFolderException(msg.toString());
		}

		return dlFolder;
	}
	
	public DLFolder findByPrimaryKey(long id) throws NoSuchFolderException, SystemException {
		DLFolder dlFolder = fetchByPrimaryKey(id);

		if (dlFolder == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
		}

		return dlFolder;
	}
	
	protected DLFolder removeImpl(DLFolder dlFolder) throws SystemException {
		try {
			repository.delete(dlFolder);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dlFolder;
	}
	
	public DLFolder update(DLFolder dlFolder, boolean isMerge) throws SystemException {
		try {
			repository.saveAndFlush(dlFolder);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}
		return dlFolder;
	}
	
	
	private static final String _SQL_SELECT_DLFOLDER = "SELECT dlFolder FROM DLFolder dlFolder";

	private static final String _SQL_SELECT_DLFOLDER_WHERE = "SELECT dlFolder FROM DLFolder dlFolder WHERE ";

	private static final String _SQL_COUNT_DLFOLDER = "SELECT COUNT(dlFolder) FROM DLFolder dlFolder";

	private static final String _SQL_COUNT_DLFOLDER_WHERE = "SELECT COUNT(dlFolder) FROM DLFolder dlFolder WHERE ";

	private static final String _SQL_GETDLFILEENTRYTYPES = "SELECT DLFileEntryType.* FROM DLFileEntryType INNER JOIN DLFileEntryTypes_DLFolders ON (DLFileEntryTypes_DLFolders.fileEntryTypeId = DLFileEntryType.fileEntryTypeId) WHERE (DLFileEntryTypes_DLFolders.folderId =:folderId)";

	private static final String _SQL_GETDLFILEENTRYTYPESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM DLFileEntryTypes_DLFolders WHERE folderId =:folderId";

	private static final String _SQL_CONTAINSDLFILEENTRYTYPE = "SELECT COUNT(*) AS COUNT_VALUE FROM DLFileEntryTypes_DLFolders WHERE folderId =:folderId AND fileEntryTypeId =:fileEntryTypeId";

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dlFolder.uuid IS NULL";

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dlFolder.uuid =:uuid";

	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dlFolder.uuid IS NULL OR dlFolder.uuid =:uuid)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dlFolder.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dlFolder.uuid =:uuid AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dlFolder.uuid IS NULL OR dlFolder.uuid =:uuid) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dlFolder.groupId =:groupId";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "dlFolder.groupId =:groupId";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "dlFolder.companyId =:companyId";
	private static final String _FINDER_COLUMN_REPOSITORYID_REPOSITORYID_2 = "dlFolder.repositoryId =:repositoryId";
	private static final String _FINDER_COLUMN_G_P_GROUPID_2 = "dlFolder.groupId =:groupId AND ";
	private static final String _FINDER_COLUMN_G_P_PARENTFOLDERID_2 = "dlFolder.parentFolderId =:parentFolderId";
	private static final String _FINDER_COLUMN_P_N_PARENTFOLDERID_2 = "dlFolder.parentFolderId =:parentFolderId AND ";
	private static final String _FINDER_COLUMN_P_N_NAME_1 = "dlFolder.name IS NULL";
	private static final String _FINDER_COLUMN_P_N_NAME_2 = "dlFolder.name =:name";
	private static final String _FINDER_COLUMN_P_N_NAME_3 = "(dlFolder.name IS NULL OR dlFolder.name =:name)";
	private static final String _FINDER_COLUMN_G_P_M_GROUPID_2 = "dlFolder.groupId =:groupId AND ";
	private static final String _FINDER_COLUMN_G_P_M_PARENTFOLDERID_2 = "dlFolder.parentFolderId =:parentFolderId AND ";
	private static final String _FINDER_COLUMN_G_P_M_MOUNTPOINT_2 = "dlFolder.mountPoint =:mountPoint";
	private static final String _FINDER_COLUMN_G_P_N_GROUPID_2 = "dlFolder.groupId =:groupId AND ";
	private static final String _FINDER_COLUMN_G_P_N_PARENTFOLDERID_2 = "dlFolder.parentFolderId =:parentFolderId AND ";
	private static final String _FINDER_COLUMN_G_P_N_NAME_1 = "dlFolder.name IS NULL";
	private static final String _FINDER_COLUMN_G_P_N_NAME_2 = "dlFolder.name =:name";
	private static final String _FINDER_COLUMN_G_P_N_NAME_3 = "(dlFolder.name IS NULL OR dlFolder.name =:name)";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "dlFolder.folderId";
	private static final String _FILTER_SQL_SELECT_DLFOLDER_WHERE = "SELECT DISTINCT {dlFolder.*} FROM DLFolder dlFolder WHERE ";
	private static final String _FILTER_SQL_SELECT_DLFOLDER_NO_INLINE_DISTINCT_WHERE_1 = "SELECT DLFolder.* FROM (SELECT DISTINCT dlFolder.folderId FROM DLFolder dlFolder WHERE ";
	private static final String _FILTER_SQL_SELECT_DLFOLDER_NO_INLINE_DISTINCT_WHERE_2 = ") TEMP_TABLE INNER JOIN DLFolder ON TEMP_TABLE.folderId = DLFolder.folderId";
	private static final String _FILTER_SQL_COUNT_DLFOLDER_WHERE = "SELECT COUNT(DISTINCT dlFolder.folderId) AS COUNT_VALUE FROM DLFolder dlFolder WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "dlFolder";
	private static final String _FILTER_ENTITY_TABLE = "DLFolder";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dlFolder.";
	private static final String _ORDER_BY_ENTITY_TABLE = "DLFolder.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DLFolder exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DLFolder exists with the key {";
	public static final String TABLE_NAME = "DLFolder";
	public static final String TABLE_SQL_CREATE = "create table DLFolder (uuid_ VARCHAR(75) null,folderId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,repositoryId LONG,mountPoint BOOLEAN,parentFolderId LONG,name VARCHAR(100) null,description STRING null,lastPostDate DATE null,defaultFileEntryTypeId LONG,overrideFileEntryTypes BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table DLFolder";
	public static final String ORDER_BY_JPQL = " ORDER BY dlFolder.parentFolderId ASC, dlFolder.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY DLFolder.parentFolderId ASC, DLFolder.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
}
