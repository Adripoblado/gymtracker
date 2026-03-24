import React from 'react';

const ExerciseFilters = ({ filters, setFilters }) => {
    const handleChange = (e) => {
        setFilters({ ...filters, [e.target.name]: e.target.value });
    };

    return (
        <div style={styles.filterBar}>
            <select name="muscleGroup" value={filters.muscleGroup} onChange={handleChange}>
                <option value="">All muscles</option>
                <option value="CHEST">Chest</option>
                <option value="BACK">Back</option>
                <option value="LEGS">Legs</option>
                <option value="SHOULDERS">Shoulders</option>
                <option value="CORE">Core</option>
                <option value="FULL_BODY">Full body</option>
                <option value="LEGS">Legs</option>
            </select>
            <select name="type" value={filters.type} onChange={handleChange}>
                <option value="">All types</option>
                <option value="STRENGTH">Strenght</option>
                <option value="CARDIO">Cardio</option>
                <option value="FLEXIBILITY">Flexibility</option>
                <option value="BALANCE">Balance</option>
                <option value="PUSH">Push</option>
                <option value="PULL">Pull</option>
                <option value="ISOMETRIC">Isometric</option>
                <option value="PLYOMETRIC">Plyometric</option>
                <option value="OTHER">Other</option>
            </select>
            <select name="equipment" value={filters.type} onChange={handleChange}>
                <option value="">Any equipment</option>
                <option value="BARBELL">Barbell</option>
                <option value="DUMBBELL">Dumbbell</option>
                <option value="MACHINE">Machine</option>
                <option value="BODYWEIGHT">Bodyweight</option>
                <option value="KETTLEBELL">Kettlebell</option>
                <option value="CABLE">Cable</option>
                <option value="RUBBERBANDS">Rubber bands</option>
                <option value="OTHER">Other</option>
            </select>
        </div>
    );
}

const styles = {
    filterBar: { display: 'flex', gap: '20px', backgroundColor: '#f4f4f4', padding: '15px', borderRadius: '8px', marginBottom: '20px', alignItems: 'flex-end' },
    group: { display: 'flex', flexDirection: 'column', gap: '5px' },
    label: { fontSize: '0.85rem', fontWeight: 'bold', color: '#555' },
    select: { padding: '8px', borderRadius: '4px', border: '1px solid #ccc' },
    resetBtn: { padding: '8px 15px', cursor: 'pointer', background: 'none', border: '1px solid #ccc', borderRadius: '4px' }
};

export default ExerciseFilters;