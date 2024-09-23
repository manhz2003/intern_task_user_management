package com.fds.nsw.liferay.service.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fds.nsw.kernel.dao.orm.BasePersistence;
import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.model.DLFileEntry;
import com.fds.nsw.liferay.repository.DLFileEntryRepository;
import com.fds.nsw.liferay.service.exception.NoSuchFileEntryException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DLFileEntryPersistenceImpl extends BasePersistence {
	
	@Autowired
	@Qualifier("lrQueryFactory")
	QueryFactory<DLFileEntry> queryFactory;
	@Autowired
	DLFileEntryRepository repository;
	public DLFileEntry create(long id) {
		DLFileEntry dlFileEntry = new DLFileEntry();

		String uuid = UUID.randomUUID().toString();

		dlFileEntry.setUuid(uuid);

		return dlFileEntry;
	}
	public DLFileEntry fetchByPrimaryKey(long fileEntryId) throws SystemException {
		DLFileEntry dlFileEntry = null;

		if (dlFileEntry == null) {

			boolean hasException = false;

			try {

				Optional<DLFileEntry> optional = repository.findById(fileEntryId);
				if (optional.isPresent()) {
					dlFileEntry = optional.get();
				}

			} catch (Exception e) {
				hasException = true;

				throw processException(e);
			} finally {

			}
		}

		return dlFileEntry;
	}
	
	public DLFileEntry findByPrimaryKey(long fileEntryId) throws NoSuchFileEntryException, SystemException {
		DLFileEntry dlFileEntry = fetchByPrimaryKey(fileEntryId);

		if (dlFileEntry == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + fileEntryId);
			}

			throw new NoSuchFileEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + fileEntryId);
		}

		return dlFileEntry;
	}
	
	public  DLFileEntry remove(DLFileEntry dlFileEntry) throws SystemException {
		try {
			repository.delete(dlFileEntry);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dlFileEntry;
	}
	
	public DLFileEntry update(DLFileEntry dlFileEntry, boolean isMerge) throws SystemException {
		try {
			repository.saveAndFlush(dlFileEntry);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dlFileEntry;
	}
	
	private static final String _SQL_SELECT_DLFILEENTRY = "SELECT dlFileEntry FROM DLFileEntry dlFileEntry";

	private static final String _SQL_SELECT_DLFILEENTRY_WHERE = "SELECT dlFileEntry FROM DLFileEntry dlFileEntry WHERE ";

	private static final String _SQL_COUNT_DLFILEENTRY = "SELECT COUNT(dlFileEntry) FROM DLFileEntry dlFileEntry";

	private static final String _SQL_COUNT_DLFILEENTRY_WHERE = "SELECT COUNT(dlFileEntry) FROM DLFileEntry dlFileEntry WHERE ";

	
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dlFileEntry.uuid IS NULL";

	
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dlFileEntry.uuid =:uuid";
	
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dlFileEntry.uuid IS NULL OR dlFileEntry.uuid =:uuid)";

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dlFileEntry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dlFileEntry.uuid =:uuid AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dlFileEntry.uuid IS NULL OR dlFileEntry.uuid =:uuid) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dlFileEntry.groupId =:groupId";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "dlFileEntry.groupId =:groupId";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "dlFileEntry.companyId =:companyId";
	private static final String _FINDER_COLUMN_MIMETYPE_MIMETYPE_1 = "dlFileEntry.mimeType IS NULL";
	private static final String _FINDER_COLUMN_MIMETYPE_MIMETYPE_2 = "dlFileEntry.mimeType =:mimeType";
	private static final String _FINDER_COLUMN_MIMETYPE_MIMETYPE_3 = "(dlFileEntry.mimeType IS NULL OR dlFileEntry.mimeType =:mimeType)";
	private static final String _FINDER_COLUMN_G_U_GROUPID_2 = "dlFileEntry.groupId =:groupId AND ";
	private static final String _FINDER_COLUMN_G_U_USERID_2 = "dlFileEntry.userId =:userId";
	private static final String _FINDER_COLUMN_G_F_GROUPID_2 = "dlFileEntry.groupId =:groupId AND ";
	private static final String _FINDER_COLUMN_G_F_GROUPID_5 = "(" + _removeConjunction(_FINDER_COLUMN_G_F_GROUPID_2)
			+ ")";
	private static final String _FINDER_COLUMN_G_F_FOLDERID_2 = "dlFileEntry.folderId =:folderId";
	private static final String _FINDER_COLUMN_G_F_FOLDERID_5 = "(" + _removeConjunction(_FINDER_COLUMN_G_F_FOLDERID_2)
			+ ")";
	private static final String _FINDER_COLUMN_G_U_F_GROUPID_2 = "dlFileEntry.groupId =:groupId AND ";
	private static final String _FINDER_COLUMN_G_U_F_GROUPID_5 = "("
			+ _removeConjunction(_FINDER_COLUMN_G_U_F_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_U_F_USERID_2 = "dlFileEntry.userId =:userId AND ";
	private static final String _FINDER_COLUMN_G_U_F_USERID_5 = "(" + _removeConjunction(_FINDER_COLUMN_G_U_F_USERID_2)
			+ ")";
	private static final String _FINDER_COLUMN_G_U_F_FOLDERID_2 = "dlFileEntry.folderId =:folderId";
	private static final String _FINDER_COLUMN_G_U_F_FOLDERID_5 = "("
			+ _removeConjunction(_FINDER_COLUMN_G_U_F_FOLDERID_2) + ")";
	private static final String _FINDER_COLUMN_G_F_N_GROUPID_2 = "dlFileEntry.groupId =:groupId AND ";
	private static final String _FINDER_COLUMN_G_F_N_FOLDERID_2 = "dlFileEntry.folderId =:folderId AND ";
	private static final String _FINDER_COLUMN_G_F_N_NAME_1 = "dlFileEntry.name IS NULL";
	private static final String _FINDER_COLUMN_G_F_N_NAME_2 = "dlFileEntry.name =:name";
	private static final String _FINDER_COLUMN_G_F_N_NAME_3 = "(dlFileEntry.name IS NULL OR dlFileEntry.name =:name)";
	private static final String _FINDER_COLUMN_G_F_T_GROUPID_2 = "dlFileEntry.groupId =:groupId AND ";
	private static final String _FINDER_COLUMN_G_F_T_FOLDERID_2 = "dlFileEntry.folderId =:folderId AND ";
	private static final String _FINDER_COLUMN_G_F_T_TITLE_1 = "dlFileEntry.title IS NULL";
	private static final String _FINDER_COLUMN_G_F_T_TITLE_2 = "dlFileEntry.title =:title";
	private static final String _FINDER_COLUMN_G_F_T_TITLE_3 = "(dlFileEntry.title IS NULL OR dlFileEntry.title =:title)";
	private static final String _FINDER_COLUMN_G_F_F_GROUPID_2 = "dlFileEntry.groupId =:groupId AND ";
	private static final String _FINDER_COLUMN_G_F_F_GROUPID_5 = "("
			+ _removeConjunction(_FINDER_COLUMN_G_F_F_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_F_F_FOLDERID_2 = "dlFileEntry.folderId =:folderId AND ";
	private static final String _FINDER_COLUMN_G_F_F_FOLDERID_5 = "("
			+ _removeConjunction(_FINDER_COLUMN_G_F_F_FOLDERID_2) + ")";
	private static final String _FINDER_COLUMN_G_F_F_FILEENTRYTYPEID_2 = "dlFileEntry.fileEntryTypeId =:fileEntryTypeId";
	private static final String _FINDER_COLUMN_G_F_F_FILEENTRYTYPEID_5 = "("
			+ _removeConjunction(_FINDER_COLUMN_G_F_F_FILEENTRYTYPEID_2) + ")";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "dlFileEntry.fileEntryId";
	private static final String _FILTER_SQL_SELECT_DLFILEENTRY_WHERE = "SELECT DISTINCT {dlFileEntry.*} FROM DLFileEntry dlFileEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_DLFILEENTRY_NO_INLINE_DISTINCT_WHERE_1 = "SELECT DLFileEntry.* FROM (SELECT DISTINCT dlFileEntry.fileEntryId FROM DLFileEntry dlFileEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_DLFILEENTRY_NO_INLINE_DISTINCT_WHERE_2 = ") TEMP_TABLE INNER JOIN DLFileEntry ON TEMP_TABLE.fileEntryId = DLFileEntry.fileEntryId";
	private static final String _FILTER_SQL_COUNT_DLFILEENTRY_WHERE = "SELECT COUNT(DISTINCT dlFileEntry.fileEntryId) AS COUNT_VALUE FROM DLFileEntry dlFileEntry WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "dlFileEntry";
	private static final String _FILTER_ENTITY_TABLE = "DLFileEntry";

	private static final String _ORDER_BY_ENTITY_ALIAS = "dlFileEntry.";

	private static final String _ORDER_BY_ENTITY_TABLE = "DLFileEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DLFileEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DLFileEntry exists with the key {";
	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}
}
