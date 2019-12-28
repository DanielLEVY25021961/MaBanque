/**
 * 
 */

$('document').ready(function() {
	
	// Fonction appelée sur click sur un des boutons ayant les 
	// classes "btn" ET "btn-warning" contenu dans le tableau identifié 
	// par id="tableListeStudent"
	$('#tableListeStudent .btn.btn-warning').on('click',function(event) {
		
		// désactive le comportement par défaut du bouton
		event.preventDefault();
		
		var href = $(this).attr('href');
		
		// récupère les données fournies par l'action 
		// "/StudentController/findById(id=${student.id})" 
		// et peuple le formulaire d'update.
		$.get(href, function(student, status) {
			
			$('#idUpdate').val(student.id);
			$('#nameUpdate').val(student.name);
			$('#departmentUpdate').val(student.department);
			$('#updatedByUpdate').val(student.updatedBy);
			$('#upDatedOnUpdate').val(student.upDatedOn);
		
		});
		
		// ouvre la boîte de dialogue updateStudentModal
		$('#updateStudentModal').modal();
		
	});

	
	// Fonction appelée sur click sur un des boutons ayant les 
	// classes "btn" ET "btn-danger" contenu dans le tableau identifié 
	// par id="tableListeStudent"
	$('#tableListeStudent .btn.btn-danger').on('click',function(event) {
		
		// désactive le comportement par défaut du bouton
		event.preventDefault();
		
		var href = $(this).attr('href');
		
		// récupère les données fournies par l'action 
		// "/StudentController/findById(id=${student.id})" 
		// et peuple le formulaire de delete.
		$.get(href, function(student, status) {
			
			$('#idDelete').val(student.id);
			$('#nameDelete').val(student.name);
			$('#departmentDelete').val(student.department);
			$('#updatedByDelete').val(student.updatedBy);
			$('#upDatedOnDelete').val(student.upDatedOn);
		
		});
		
		// ouvre la boîte de dialogue deleteStudentModal
		$('#deleteStudentModal').modal();
		
	});

});