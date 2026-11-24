package view;
import exceptions.MatriculaBloqueadaException;
import model.AlunoAcademia;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TelaAcademia extends JFrame implements ActionListener {
    private JLabel lblMatricula, lblNome, lblIdade, lblSexo, lblPeso, lblAltura, lblMensalidade;
    private JTextField txtMatricula, txtNome, txtIdade, txtSexo, txtPeso, txtAltura, txtMensalidade;
    private JButton btnCadastrar, btnPesquisar, btnFrequencia, btnBloquear, btnOrdenar, btnSair, btnAtualizar;
    private JTextArea txtMsg;
    private JScrollPane scrollMsg;
    private List<AlunoAcademia> alunos = new ArrayList<>();

    public TelaAcademia() {
        this.setSize(620, 560);
        this.setTitle("Sistema de Academia e Fichas de Treino");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLayout(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sairDoSistema();
            }
        });

        // Lado esquerdo
        lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setBounds(25, 25, 80, 25);
        this.add(lblMatricula);

        txtMatricula = new JTextField();
        txtMatricula.setBounds(105, 25, 160, 25);
        this.add(txtMatricula);

        lblNome = new JLabel("Nome:");
        lblNome.setBounds(25, 60, 80, 25);
        this.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(105, 60, 160, 25);
        this.add(txtNome);

        lblIdade = new JLabel("Idade:");
        lblIdade.setBounds(25, 95, 80, 25);
        this.add(lblIdade);

        txtIdade = new JTextField();
        txtIdade.setBounds(105, 95, 160, 25);
        this.add(txtIdade);

        lblSexo = new JLabel("Sexo:");
        lblSexo.setBounds(25, 130, 80, 25);
        this.add(lblSexo);

        txtSexo = new JTextField();
        txtSexo.setBounds(105, 130, 160, 25);
        this.add(txtSexo);

        // Lado direito
        lblPeso = new JLabel("Peso (kg):");
        lblPeso.setBounds(310, 25, 95, 25);
        this.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(410, 25, 170, 25);
        this.add(txtPeso);

        lblAltura = new JLabel("Altura (m):");
        lblAltura.setBounds(310, 60, 95, 25);
        this.add(lblAltura);

        txtAltura = new JTextField();
        txtAltura.setBounds(410, 60, 170, 25);
        this.add(txtAltura);

        lblMensalidade = new JLabel("Mensalidade:");
        lblMensalidade.setBounds(310, 95, 95, 25);
        this.add(lblMensalidade);

        txtMensalidade = new JTextField();
        txtMensalidade.setBounds(410, 95, 170, 25);
        this.add(txtMensalidade);

        // Aqui são os botões
        btnCadastrar = criarBotao("Cadastrar", 25, 180);
        btnPesquisar = criarBotao("Pesquisar", 215, 180);
        btnFrequencia = criarBotao("Reg. Frequência", 405, 180);

        btnBloquear = criarBotao("Bloquear/Ativar", 25, 220);
        btnOrdenar = criarBotao("Listar Ordenado", 215, 220);
        btnSair = criarBotao("Sair", 405, 220);

        btnAtualizar = criarBotao("Atualizar Dados", 25, 260);

        // Relatório é aqui
        txtMsg = new JTextArea();
        txtMsg.setEditable(false);
        scrollMsg = new JScrollPane(txtMsg);
        scrollMsg.setBounds(25, 305, 555, 195);
        this.add(scrollMsg);
    }
    private JButton criarBotao(String texto, int x, int y) {
        JButton btn = new JButton(texto);
        btn.setBounds(x, y, 175, 28);
        btn.addActionListener(this);
        this.add(btn);
        return btn;
    }
    private void sairDoSistema() {
        JOptionPane.showMessageDialog(this, "Saindo do sistema da academia...", "Fim do programa", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
    private AlunoAcademia buscarPorMatricula(int matricula) {
        for (AlunoAcademia aluno : alunos) {
            if (aluno.getMatricula() == matricula) {
                return aluno;
            }
        }
        return null;
    }
    private void limparCampos() {
        txtMatricula.setText("");
        txtNome.setText("");
        txtIdade.setText("");
        txtSexo.setText("");
        txtPeso.setText("");
        txtAltura.setText("");
        txtMensalidade.setText("");
        txtMatricula.requestFocus();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSair) {
            sairDoSistema();
            return;
        }
        try {
            if (e.getSource() == btnCadastrar) {
                int matricula = Integer.parseInt(txtMatricula.getText());
                if (buscarPorMatricula(matricula) != null) {
                    JOptionPane.showMessageDialog(this, "Já existe um aluno com essa matrícula!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String nome = txtNome.getText();
                int idade = Integer.parseInt(txtIdade.getText());
                String sexo = txtSexo.getText();
                double peso = Double.parseDouble(txtPeso.getText().replace(",", "."));
                double altura = Double.parseDouble(txtAltura.getText().replace(",", "."));
                double mensalidade = Double.parseDouble(txtMensalidade.getText().replace(",", "."));

                AlunoAcademia novo = new AlunoAcademia(matricula, nome, idade, sexo, peso, altura, mensalidade);
                alunos.add(novo);
                txtMsg.append("Cadastrado: " + novo + "\n");
                limparCampos();

            } else if (e.getSource() == btnPesquisar) {
                int matricula = Integer.parseInt(txtMatricula.getText());
                AlunoAcademia encontrado = buscarPorMatricula(matricula);
                if (encontrado != null) {
                    txtNome.setText(encontrado.getNome());
                    txtIdade.setText(String.valueOf(encontrado.getIdade()));
                    txtSexo.setText(encontrado.getSexo());
                    txtPeso.setText(String.valueOf(encontrado.getPeso()));
                    txtAltura.setText(String.valueOf(encontrado.getAltura()));
                    txtMensalidade.setText(String.valueOf(encontrado.getMensalidade()));
                    txtMsg.append("Encontrado -> " + encontrado + "\n");
                } else {
                    JOptionPane.showMessageDialog(this, "Aluno não encontrado!", "Pesquisa", JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (e.getSource() == btnAtualizar) {
                int matricula = Integer.parseInt(txtMatricula.getText());
                AlunoAcademia encontrado = buscarPorMatricula(matricula);
                if (encontrado != null) {
                    encontrado.setNome(txtNome.getText());
                    encontrado.setIdade(Integer.parseInt(txtIdade.getText()));
                    encontrado.setSexo(txtSexo.getText());
                    encontrado.setPeso(Double.parseDouble(txtPeso.getText().replace(",", ".")));
                    encontrado.setAltura(Double.parseDouble(txtAltura.getText().replace(",", ".")));
                    encontrado.setMensalidade(Double.parseDouble(txtMensalidade.getText().replace(",", ".")));
                    txtMsg.append("Cadastro atualizado -> " + encontrado + "\n");
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Informe uma matrícula válida para atualizar!", "Atualização", JOptionPane.WARNING_MESSAGE);
                }

            } else if (e.getSource() == btnFrequencia) {
                int matricula = Integer.parseInt(txtMatricula.getText());
                AlunoAcademia encontrado = buscarPorMatricula(matricula);
                if (encontrado != null) {
                    encontrado.registrarFrequencia();
                    txtMsg.append("Frequência registrada para " + encontrado.getNome() + " (Total: " + encontrado.getFrequencia() + ")\n");
                } else {
                    JOptionPane.showMessageDialog(this, "Aluno não encontrado!", "Frequência", JOptionPane.WARNING_MESSAGE);
                }

            } else if (e.getSource() == btnBloquear) {
                int matricula = Integer.parseInt(txtMatricula.getText());
                AlunoAcademia encontrado = buscarPorMatricula(matricula);
                if (encontrado != null) {
                    encontrado.setBloqueado(!encontrado.isBloqueado());
                    String status = encontrado.isBloqueado() ? "BLOQUEADO" : "REATIVADO";
                    txtMsg.append("Status alterado (" + status + "): " + encontrado.getNome() + "\n");
                } else {
                    JOptionPane.showMessageDialog(this, "Aluno não encontrado!", "Status", JOptionPane.WARNING_MESSAGE);
                }

            } else if (e.getSource() == btnOrdenar) {
                Collections.sort(alunos);
                txtMsg.append("\n--- LISTA ORDENADA POR MATRÍCULA ---\n");
                for (AlunoAcademia a : alunos) {
                    txtMsg.append(a.toString() + "\n");
                }
                txtMsg.append("------------------------------------\n");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Verifique os campos numéricos (Matrícula, Idade, Peso, Altura, Mensalidade)!", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (MatriculaBloqueadaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Matrícula Bloqueada", JOptionPane.ERROR_MESSAGE);
        }
    }
}