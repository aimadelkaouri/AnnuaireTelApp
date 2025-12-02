// import React, { useEffect, useState } from 'react';
// import axios from 'axios';

// function Display() {
//   const [utilisateur, setUtilisateur] = useState([]);

//   useEffect(() => {
//     axios.get('http://localhost:8080/api/utilisateurs')
//       .then(res => setUtilisateur(res.data))
//       .catch(err => console.error('Erreur:', err));
//   }, []);

//   if (!utilisateur) return <p>Chargement...</p>;

//   return (
//     <div style={{ maxWidth: '600px', margin: '0 auto' }}>
//       <h2>Liste des utilisateurs</h2>
//       {utilisateur.length === 0 ? (
//         <p>Aucun étudiant trouvé.</p>
//       ) : (
//         <ul>
//           {utilisateur.map(user => (
//             <li key={user.id}>
//               {user.nom} {user.email} <a>modifier</a> <a>supprimer</a>
//             </li>
//           ))}
//         </ul>
//       )}
//     </div>
//   );
// }
// export default Display;



import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Display() {
  const [utilisateurs, setUtilisateurs] = useState(null); // null = chargement
  const [erreur, setErreur] = useState(null);

  useEffect(() => {
    axios.get('http://localhost:8080/api/utilisateurs')
      .then(res => {
        // Vérifie si res.data est un tableau ou un objet contenant le tableau
        if (Array.isArray(res.data)) {
          setUtilisateurs(res.data);
        } else if (res.data.utilisateurs) {
          setUtilisateurs(res.data.utilisateurs);
        } else {
          setUtilisateurs([]); // Aucun utilisateur trouvé
        }
      })
      .catch(err => {
        console.error('Erreur:', err);
        setErreur('Impossible de récupérer les utilisateurs');
        setUtilisateurs([]); // pour ne pas bloquer le rendu
      });
  }, []);

  if (utilisateurs === null) return <p>Chargement...</p>;
  if (erreur) return <p style={{ color: 'red' }}>{erreur}</p>;

  return (
    <div style={{ maxWidth: '600px', margin: '0 auto' }}>
      <h2>Liste des utilisateurs</h2>
      {utilisateurs.length === 0 ? (
        <p>Aucun utilisateur trouvé.</p>
      ) : (
        <ul>
          {utilisateurs.map(user => (
            <li key={user.id}>
              {user.nom} ({user.email}) 
              <a href="#" style={{ marginLeft: '10px' }}>modifier</a> 
              <a href="#" style={{ marginLeft: '5px' }}>supprimer</a>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default Display;

