package com.example.data.dao

import androidx.room.*
import com.example.data.model.MonthlyOverride
import com.example.data.model.SalarySettings
import com.example.data.model.WorkEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface SalaryDao {

    // --- Work Entries ---
    @Query("SELECT * FROM work_entries ORDER BY dateString ASC")
    fun getAllWorkEntries(): Flow<List<WorkEntry>>

    @Query("SELECT * FROM work_entries ORDER BY dateString ASC")
    suspend fun getAllWorkEntriesDirect(): List<WorkEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkEntries(entries: List<WorkEntry>)

    @Query("DELETE FROM work_entries")
    suspend fun deleteAllWorkEntries()

    @Query("SELECT * FROM work_entries WHERE dateString LIKE :monthPattern ORDER BY dateString ASC")
    fun getWorkEntriesForMonth(monthPattern: String): Flow<List<WorkEntry>>

    @Query("SELECT * FROM work_entries WHERE dateString = :dateLimit LIMIT 1")
    suspend fun getWorkEntryByDate(dateLimit: String): WorkEntry?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateWorkEntry(entry: WorkEntry)

    @Delete
    suspend fun deleteWorkEntry(entry: WorkEntry)

    @Query("DELETE FROM work_entries WHERE dateString = :dateStr")
    suspend fun deleteWorkEntryByDate(dateStr: String)

    // --- Settings ---
    @Query("SELECT * FROM salary_settings WHERE id = 1 LIMIT 1")
    fun getSettingsFlow(): Flow<SalarySettings?>

    @Query("SELECT * FROM salary_settings WHERE id = 1 LIMIT 1")
    suspend fun getSettingsDirect(): SalarySettings?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateSettings(settings: SalarySettings)

    // --- Monthly Overrides ---
    @Query("SELECT * FROM monthly_overrides WHERE monthStr = :monthStr LIMIT 1")
    fun getMonthlyOverrideFlow(monthStr: String): Flow<MonthlyOverride?>

    @Query("SELECT * FROM monthly_overrides WHERE monthStr = :monthStr LIMIT 1")
    suspend fun getMonthlyOverrideDirect(monthStr: String): MonthlyOverride?

    @Query("SELECT * FROM monthly_overrides")
    fun getAllMonthlyOverridesFlow(): Flow<List<MonthlyOverride>>

    @Query("SELECT * FROM monthly_overrides")
    suspend fun getAllMonthlyOverridesDirect(): List<MonthlyOverride>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMonthlyOverrides(overrides: List<MonthlyOverride>)

    @Query("DELETE FROM monthly_overrides")
    suspend fun deleteAllMonthlyOverrides()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateMonthlyOverride(override: MonthlyOverride)
}
