package com.example.broc.presentation.f_dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.broc.R
import com.example.broc.presentation.f_home.HomeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class InviteCancelDialogFragment(private val buttonsChecked: Boolean = false) : DialogFragment() {
    private val viewModel: HomeViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext()).setTitle("Cancel Invites")
            .setMultiChoiceItems(
                viewModel.emails.value.map { it.email }.toTypedArray(),
                viewModel.emails.value.map{!it.active}.toBooleanArray()) { _, which, _ ->
                    viewModel.emails.value[which].active = !viewModel.emails.value[which].active
            }
            .setNegativeButton("Cancel") { _, _ ->
            }.setPositiveButton("Confirm") { dialog, _ ->
                viewModel.cancelInvites()
            }.create()
}