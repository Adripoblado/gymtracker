import React from 'react';

const WorkoutFilters = ({ filters, setFilters, catalogs }) => {

    const handleChange = (e) => {
        setFilters({ ...filters, [e.target.name]: e.target.value });
    };

    const clearFilters = () => {
        setFilters({ startDate: '', endDate: '', muscleGroupId: '', sortBy: 'date_desc'});
    };

    return (
        <div style={styles.filterBar}>
            
            <div style={styles.group}>
                <label style={styles.label}>From Date</label>
                <input type="date" name="startDate" value={filters.startDate} onChange={handleChange} style={styles.input} />
            </div>

            <div style={styles.group}>
                <label style={styles.label}>To Date</label>
                <input type="date" name="endDate" value={filters.endDate} onChange={handleChange} style={styles.input} />
            </div>

            <div style={styles.group}>
                <label style={styles.label}>Muscle Group</label>
                <select name="muscleGroupId" value={filters.muscleGroupId} onChange={handleChange} style={styles.input}>
                    <option value="">All Muscles</option>
                    {catalogs?.muscleGroups?.map(muscle => (
                        <option key={muscle.id} value={muscle.id}>{muscle.name}</option>
                    ))}
                </select>
            </div>

            <div style={styles.group}>
                <label style={styles.label}>Sort By</label>
                <select name="sortBy" value={filters.sortBy} onChange={handleChange} style={styles.input}>
                    <option value="date_desc">Newest First</option>
                    <option value="date_asc">Oldest First</option>
                </select>
            </div>

            <button style={styles.clearBtn} onClick={clearFilters}>Clear</button>
        </div>
    );
};

const styles = {
    filterBar: { display: 'flex', flexWrap: 'wrap', gap: '20px', backgroundColor: '#f8f9fa', padding: '15px', borderRadius: '12px', marginBottom: '20px', alignItems: 'flex-end', border: '1px solid #eee' },
    group: { display: 'flex', flexDirection: 'column', gap: '5px' },
    label: { fontSize: '0.85rem', fontWeight: 'bold', color: '#555' },
    input: { padding: '10px', borderRadius: '6px', border: '1px solid #ccc', outline: 'none', fontFamily: 'inherit' },
    clearBtn: { padding: '10px 15px', cursor: 'pointer', background: 'white', color: '#6c757d', border: '1px solid #ccc', borderRadius: '6px', fontWeight: 'bold', transition: '0.2s' }
};

export default WorkoutFilters;