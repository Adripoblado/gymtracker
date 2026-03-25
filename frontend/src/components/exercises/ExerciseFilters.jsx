import React from 'react';

const ExerciseFilters = ({ filters, setFilters, catalogs }) => {
    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        const finalValue = type === 'checkbox' ? checked : value;
        setFilters(prev => ({ 
            ...prev,
            [name]: finalValue
         }));
    };

    return (
        <div style={styles.filterBar}>
            <select name="muscleGroupId" value={filters.muscleGroupId} onChange={handleChange} style={styles.select}>
                <option value="">All muscles</option>
                {catalogs.muscleGroups.map((muscle) => (
                    <option key={muscle.id} value={muscle.id}>
                        {muscle.name}
                    </option>
                ))}
            </select>
            <select name="exerciseTypeId" value={filters.exerciseTypeId} onChange={handleChange} style={styles.select}>
                <option value="">All types</option>
                {catalogs.exerciseTypes.map((type) => (
                    <option key={type.id} value={type.id}>
                        {type.name}
                    </option>
                ))}
            </select>
            <select name="equipmentId" value={filters.equipmentId} onChange={handleChange} style={styles.select}>
                <option value="">Any equipment</option>
                {catalogs.equipments.map((equip) => (
                    <option key={equip.id} value={equip.id}>
                        {equip.name}
                    </option>
                ))}
            </select>
            <label style={styles.checkboxLabel}>
                <input
                    type="checkbox"
                    name="onlyMine"
                    checked={filters.onlyMine || false}
                    onChange={handleChange}
                    style={styles.checkbox}
                />
                <span>Show only my exercises</span>
            </label>
        </div>
    );
}

const styles = {
    filterBar: { display: 'flex', gap: '20px', backgroundColor: '#f4f4f4', padding: '15px', borderRadius: '8px', marginBottom: '20px', alignItems: 'flex-end' },
    group: { display: 'flex', flexDirection: 'column', gap: '5px' },
    label: { fontSize: '0.85rem', fontWeight: 'bold', color: '#555' },
    select: { padding: '8px', borderRadius: '4px', border: '1px solid #ccc' },
    resetBtn: { padding: '8px 15px', cursor: 'pointer', background: 'none', border: '1px solid #ccc', borderRadius: '4px' },
    checkboxLabel: { display: 'flex', alignItems: 'center', gap: '8px', fontSize: '0.9rem', fontWeight: '500', color: '#495057', cursor: 'pointer', paddingLeft: '10px', borderLeft: '2px solid #dee2e6' },
    checkbox: { width: '18px', height: '18px', cursor: 'pointer' }
};

export default ExerciseFilters;